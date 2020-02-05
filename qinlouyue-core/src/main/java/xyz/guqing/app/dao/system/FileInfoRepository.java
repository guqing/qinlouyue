package xyz.guqing.app.dao.system;

import xyz.guqing.app.bean.entity.system.FileInfo;
import xyz.guqing.app.dao.BaseRepository;

public interface FileInfoRepository  extends BaseRepository<FileInfo,Long> {
    FileInfo findByRealFileName(String fileName);
}
