package com.sample.microservices.kafka.data.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.sample.microservices.common.data.model.BaseEntity;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="kafka_consumer_message")

@Getter
@Setter
public class KafkaConsumerMessageEntity extends BaseEntity {
	
	private static final long serialVersionUID = 8436014022103292703L;
	
	@Column(name="topic_num")
	private String topic;
	
	@Column(name="group_num")
	private String group;
	
	@Column(name="message")
	private String message;
	
	@Column(name="partition_num")
	private Integer partition;
	
	@Column(name="offset_num")
	private Integer offset;
	
	@Column(name="created_date")
	private LocalDate createdDate;
	
}
