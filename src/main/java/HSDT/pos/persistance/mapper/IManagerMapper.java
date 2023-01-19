package HSDT.pos.persistance.mapper;

import HSDT.pos.dto.ManagerDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface IManagerMapper {

    //회원가입하기
    int InsertManager(ManagerDTO pDTO) throws Exception;

    //로그인 시 아이디 비밀번호 일치하는지 확인하기
    ManagerDTO getManagerLoginCheck(ManagerDTO pDTO) throws Exception;

    //회원탈퇴
    int deleteManager(ManagerDTO pDTO) throws Exception;

    //회원정보 수정
    void updateManager(ManagerDTO pDTO) throws Exception;

}
