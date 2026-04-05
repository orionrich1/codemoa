package com.codemoa.project.common.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

/**
 * 2-2: 파일 업로드 로직 공통화.
 * InformationController.saveUploadedFile(), TeamRecruitServiceImpl.updateTeamRecruit(),
 * TeamRecruitController.writeRecruit() 에서 중복되던 UUID 파일명 생성 + 디렉터리 생성 + transferTo 로직을 통합.
 */
@Service
public class FileStorageService {

    @Value("${file.upload.dir}")
    private String uploadBaseDir;

    /**
     * 파일을 {uploadBaseDir}/{subDirectory}/ 아래에 UUID 파일명으로 저장합니다.
     *
     * @param file         업로드된 파일 (null 또는 empty이면 null 반환)
     * @param subDirectory 저장할 하위 디렉터리 (예: "information", "recruit")
     * @return 저장된 파일명, 파일이 없으면 null
     */
    public String store(MultipartFile file, String subDirectory) throws IOException {
        if (file == null || file.isEmpty()) {
            return null;
        }
        Path dir = Paths.get(uploadBaseDir, subDirectory);
        Files.createDirectories(dir);

        String ext = StringUtils.getFilenameExtension(file.getOriginalFilename());
        String saveName = UUID.randomUUID() + (ext != null && !ext.isBlank() ? "." + ext : "");
        file.transferTo(dir.resolve(saveName).toFile());
        return saveName;
    }

    /**
     * 기존 파일을 삭제합니다. 파일이 없거나 오류가 발생해도 예외를 던지지 않습니다.
     *
     * @param subDirectory 하위 디렉터리
     * @param filename     삭제할 파일명 (null이면 무시)
     */
    public void delete(String subDirectory, String filename) {
        if (filename == null || filename.isBlank()) return;
        try {
            Files.deleteIfExists(Paths.get(uploadBaseDir, subDirectory, filename));
        } catch (IOException ignored) {
        }
    }
}
