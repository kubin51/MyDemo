package util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * 文件操作工具类
 *
 * @author kubin
 * @version V1.0
 * @Package util
 * @date 2020/12/29 15:00
 */
public class FileUtils {

    private static final Logger LOGGER = LoggerFactory.getLogger(FileUtils.class);
    /**
     * 已上传的文件的md5值，暂时存在内存中，后续可以持久化到数据库中
     */
    public static Map<String, String> fileMd5s = new ConcurrentHashMap<>();
    /**
     * 已上传的块序号集合，暂时存在内存中，后续可以持久化到数据库中
     */
    public static List<String> chunkNumbers = new CopyOnWriteArrayList<>();
    /**
     * 上传文件的存放位置，暂时写死，后续可通过配置获取
     */
    public static final String TARGET_PATH = "C:\\Users\\lenovo\\Desktop";

    /**
     * 分块上传文件
     *
     * @param fileName     文件名称
     * @param fileMd5      文件的MD5码
     * @param fileSize     文件大小
     * @param totalChunk   总的分块数
     * @param currentChunk 当前分块序号
     * @param chunkFile    当前分块文件
     */
    public static void uploadBigFile(String fileName, String fileMd5, Long fileSize, Integer totalChunk, Integer currentChunk, File chunkFile) {
        // 输入校验
        if (totalChunk == null || totalChunk == 0) {
            LOGGER.info("totalChunk is null or zero");
            throw new RuntimeException("totalChunk is null or zero");
        }
        // 校验MD5值，实现秒传
        if (!fileMd5s.containsKey(fileMd5)) {
            String targetFile = TARGET_PATH + fileName;
            String chunkNumber = currentChunk + "_" + fileMd5;
            try {
                // 校验该块是否已上传，实现断点续传
                if(!chunkNumbers.contains(chunkNumber)){
                    long chunkFileSize = chunkFile.length();
                    // 计算光标的位置，即开始写入字符的位置
                    Long offset = null;
                    if (currentChunk == totalChunk - 1) {
                        offset = fileSize - chunkFileSize;
                    } else {
                        offset = currentChunk * chunkFileSize;
                    }
                    RandomAccessFile randomAccessFile = new RandomAccessFile(targetFile, "rw");
                    randomAccessFile.setLength(fileSize);
                    randomAccessFile.seek(offset);
                    FileInputStream fis = new FileInputStream(chunkFile);
                    byte[] buf = new byte[1024];
                    int len;
                    while (-1 != (len = fis.read(buf))) {
                        randomAccessFile.write(buf, 0, len);
                    }
                    randomAccessFile.close();
                    chunkNumbers.add(chunkNumber);
                }
            } catch (IOException e) {
                LOGGER.error("upload failed", e);
                throw new RuntimeException("upload failed");
            }
            fileMd5s.put(fileMd5, fileName);
        }
    }
}
