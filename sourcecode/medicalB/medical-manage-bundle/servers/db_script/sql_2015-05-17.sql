
-- 添加顶级专家字段
ALTER TABLE `tb_medical_doctor` 
   ADD COLUMN `specialist` INT DEFAULT '0' NULL COMMENT '专家描述：0非顶级专家,1顶级专家' AFTER `isVisit`;