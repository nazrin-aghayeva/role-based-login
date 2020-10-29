package com.employee.app.entities;

import lombok.*;

@Getter
@Setter
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Mail {
    public String from;
    public String to;
    public String content;
    public String subject;
}
