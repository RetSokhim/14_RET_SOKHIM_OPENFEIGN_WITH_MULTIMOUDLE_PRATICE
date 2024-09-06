package org.example.response;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
public class CustomerResponse {
    private Long Id;
    private String name;
    private String email;
}
