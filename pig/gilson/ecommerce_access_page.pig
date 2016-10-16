lines = load '/home/hadoop/files/ecommerce.log' using TextLoader(); --carrega arquivo não estruturado

lines_filter = filter lines by ($0 matches '.*Started GET.*for.*at.*$'); --obtém somentes as linhas com as chamadas GET (aqui eu coloquei esse RegExp pq da pra tirar os IP’s apartir dessa varia tb)

pages = foreach lines_filter generate REGEX_EXTRACT ($0, '.*"(.*)"',1) as paginas;     --extrai, via expressão regular, as paginas do string

grouped_pages = group pages by paginas; --agrupa as paginas

count_pages = foreach grouped_pages generate group, COUNT (pages.paginas); --contabiliza o agrupamento

dump count_pages;

