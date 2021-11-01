package com.js.freeproject.domain.file.application;

//import com.js.freeproject.domain.amazonS3.S3Service;
import com.js.freeproject.domain.amazonS3.S3Service;
import com.js.freeproject.domain.board.application.BoardService;
import com.js.freeproject.domain.board.domain.Board;
import com.js.freeproject.domain.board.domain.BoardRepository;
import com.js.freeproject.domain.file.domain.BoardFile;
import com.js.freeproject.domain.file.domain.BoardFileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BoardFileService {

    private final BoardFileRepository boardFileRepository;
    private final BoardRepository boardRepository;
    private final S3Service s3Service;

    //파일 조회 - BoardService에서 게시글 1개 조회시 해당 ID에 파일이 있으면 조회
    public List<BoardFile> findBoardFiles(final Long boardId){
        List<BoardFile> searchBoardFiles = boardFileRepository.findAllByBoardId(boardId);
        return searchBoardFiles;
    }

    //파일 삽입 - 게시글 등록과 동시에 파일이 있다면 실행
    public void saveBoardFiles(List<MultipartFile> files, final Long boardId) throws IOException {
        Board searchBoard = boardRepository.findById(boardId).orElseThrow(IllegalArgumentException::new);
        for (MultipartFile file:files) {
            BoardFile uploadFile = s3Service.upload(file,"board");
            //여기서 에러 뜰까?
            uploadFile.updateBoardId(searchBoard);
            boardFileRepository.save(uploadFile);
        }
    }

    //파일 수정
    public void updateBoardFile(List<MultipartFile> upFiles, final Long boardId) throws IOException {
        List<BoardFile> delFiles = boardFileRepository.findAllByBoardId(boardId);
        for (BoardFile boardFile:delFiles) {
            s3Service.deleteFile(boardFile.getName());
        }
        saveBoardFiles(upFiles,boardId);
    }
}
