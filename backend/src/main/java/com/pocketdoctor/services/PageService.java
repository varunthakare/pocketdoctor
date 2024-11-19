package com.pocketdoctor.services;


import com.pocketdoctor.model.DoctorData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PageService {


    @Autowired
    private DoctorService doctorService;


}
