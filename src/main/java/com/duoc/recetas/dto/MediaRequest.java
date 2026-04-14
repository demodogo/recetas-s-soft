package com.duoc.recetas.dto;


import com.duoc.recetas.model.MediaType;

import lombok.Data;

@Data
public class MediaRequest {
  private String url;
  private MediaType type;
}
