package com.weeznha.leowallet.service;

import com.weeznha.leowallet.dto.UserDto;
import com.weeznha.leowallet.dto.WalletDto;
import com.weeznha.leowallet.model.User;
import com.weeznha.leowallet.model.Wallet;
import com.weeznha.leowallet.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;
    
    public Boolean createUser(UserDto userDto){
        if(exists(userDto)) return false;
        ModelMapper modelMapper = new ModelMapper();
        User user = new User();
        modelMapper.map(userDto, user);
        WalletDto walletDto = new WalletDto();
        Wallet wallet = new Wallet();
        modelMapper.map(walletDto,wallet);
        user.setWallet(wallet);

        userRepository.save(user);
        return true;
    }

    private Boolean exists(UserDto userDto){
        if (userDto.getUserName().isEmpty() || userDto.getPassword().isEmpty()){
            //TODO add exception
            //in this case I need to add an exception to notify of the fact that the fields are
            //empty
            return true;
        }
        List<User> all = userRepository.findAll();
        List<User> users = all
                .stream()
                .filter(user -> user.getUserName().equalsIgnoreCase(userDto.getUserName()))
                .collect(Collectors.toList());
        if (users.isEmpty()){
            //TODO add exception
            return false;
        }
        return true;
    }
}
