-- Totaliza por data, url, 

A = LOAD 'hdfs://localhost:9000/Pig_Data/*.log'  USING PigStorage (' ') ;
B = FILTER A BY ( $0 ==  'I,' AND $4 == 'INFO' and $8 == 'GET'  and $9 matches '.*products/.*');
C = FOREACH B generate $13 as data, $11 as IP,$9 as url;
D = GROUP C BY (data, url);
E = FOREACH D GENERATE FLATTEN(group) as (data,url),  COUNT(C);
DUMP E;
