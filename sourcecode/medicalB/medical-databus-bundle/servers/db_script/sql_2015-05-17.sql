-- 添加退回分诊类型
ALTER TABLE `tb_medical_consultation` 
ADD COLUMN `isdispatcher` INT NULL COMMENT '是否分诊推荐' AFTER `isnotice`, 
ADD COLUMN `dispatcherType` INT NULL COMMENT '分诊推荐类型：1推荐医生，2推荐科室' AFTER `isdispatcher`;
-- 分诊推荐表
CREATE TABLE `tb_medical_consultation_dispatcher` (
  `dispatcherId` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `consultationID` BIGINT(20) DEFAULT NULL,
  `dispatcherType` INT(11) DEFAULT NULL COMMENT '1:医生2:科室',
  `targetId` BIGINT(20) DEFAULT NULL,
  PRIMARY KEY (`dispatcherId`)
);
-- 添加顶级专家字段
ALTER TABLE `tb_medical_doctor` 
   ADD COLUMN `specialist` INT DEFAULT '0' NULL COMMENT '专家描述：0非顶级专家,1顶级专家' AFTER `isVisit`;