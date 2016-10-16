A = LOAD '/user/fsw/luiz/livro1.txt';
B = foreach A generate flatten(TOKENIZE((chararray)$0)) as word;
C = group B by word;
D = foreach C generate COUNT(B), group;
store D into '/user/fsw/luiz/wordcount';
