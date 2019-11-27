package com.milos.releasemicroservice.service.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class LabelDetailDTO extends ItemDetailDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private String profile;
    private String contactInfo;
    private List<ReleaseInItemDTO> releases;
}
