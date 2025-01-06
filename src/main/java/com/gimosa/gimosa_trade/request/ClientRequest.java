package com.gimosa.gimosa_trade.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ClientRequest {
    private String name;
    private String address;
    private String oib;
    private String email;
    private String phone;
    private String date;
    private Integer pallets;
    private Integer packages;
}
