package HSDT.pos.service.impl;

import HSDT.pos.persistance.mapper.IManagerMapper;
import HSDT.pos.service.IManegerService;
import HSDT.pos.dto.ManagerDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service(value = "ManagerService")
@RequiredArgsConstructor
public class ManagerService implements IManegerService {

    @Autowired
    private IManagerMapper managerMapper;

    //-----------회원가입-----------------
    @Override
    public int InsertManager(ManagerDTO pDTO) throws Exception {

        int res =0;

        if(pDTO == null){
            pDTO = new ManagerDTO();
        }

            //------데이터베이스에 회원정보 insert
            int success = managerMapper.InsertManager(pDTO);

            if (success > 0) {
                res = 1;
            } else {
                res = 0;
            }
        return res;
        }


    //---------로그인--------------------
    @Override
    public ManagerDTO getManagerLoginCheck(ManagerDTO pDTO) throws Exception {
        if (pDTO == null){
            pDTO = new ManagerDTO();
        }
        return managerMapper.getManagerLoginCheck(pDTO);
    }

    //-----------회원탈퇴------------------
    @Transactional
    @Override
    public int deleteManager(ManagerDTO pDTO) throws Exception {

        log.info(this.getClass().getName() + ".Delete Manager!!");

        int res = managerMapper.deleteManager(pDTO);

        return res;
    }

    //--------- 회원정보 수정 ---------------
    @Transactional
    @Override
    public void updateManager(ManagerDTO pDTO) throws Exception {

        log.info(this.getClass().getName() + ".updateManager START!");

        managerMapper.updateManager(pDTO);

        log.info(this.getClass().getName() + ".updateManager End!");
    }
}
