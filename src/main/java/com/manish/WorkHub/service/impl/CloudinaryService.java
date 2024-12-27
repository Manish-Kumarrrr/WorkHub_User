package com.manish.WorkHub.service.impl;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class CloudinaryService {

    private final Cloudinary cloudinary;

    public CloudinaryService() {
        this.cloudinary = new Cloudinary(ObjectUtils.asMap(
                "cloud_name", "dwjavbich",
                "api_key", "232823611865172",
                "api_secret", "C1N6_laJXuLtfDg-eiJEpB1fuk0"
        ));
    }

    public Map<String, String> generateUploadSignature() {
        try {
            Map<String, Object> params = new HashMap<>();
            params.put("timestamp", System.currentTimeMillis() / 1000L); // seconds
            String signature = cloudinary.apiSignRequest(params, "C1N6_laJXuLtfDg-eiJEpB1fuk0");
            Map<String, String> output = new HashMap<>();
            output.put("signature", signature);
            output.put("api_key", "232823611865172");

            return output;
        } catch (Exception e) {
            throw new RuntimeException("Failed to generate upload signature", e);
        }
    }
}
