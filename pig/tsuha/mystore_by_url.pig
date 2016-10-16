A = LOAD '/home/renatojava/dev/hadoop/ecommerce/log/*.log'  USING PigStorage (' ') ;
B = FILTER A BY ( $0 ==  'I,' AND $4 == 'INFO' and $8 == 'GET');
C = group B by $9;
D = foreach C generate COUNT(B) as total, group as url;
DUMP D;
explain D;

