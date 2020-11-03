package com.example.tasklist.entity;

import com.example.tasklist.util.TaskStatus;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    private String descricao;
    private TaskStatus status;
    @CreationTimestamp
    private Date dataCriacao;
    @UpdateTimestamp
    private Date DataEdicao;
    private Date DataConclusao;


}
