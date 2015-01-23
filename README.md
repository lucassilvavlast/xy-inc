# xy-inc
A aplicação foi desenvolvida para o banco de dados PostgreSQL<br>
Caso o banco de dados não esteja instalado na máquina onde será executado o projeto, é possível fazer o download pelo link http://www.postgresql.org/download/<br>
Após instalar o banco de dados, atualize o usuário e senha de acesso no arquivo persistence.xml<br>
Caso queira fazer os testes em outro banco de dados é necessária apenas a inclusão do driver como dependência utilizando o Maven e alterar o driver que esta sendo utilizado no arquivo persistence.xml<br>
Execute o script "create-db-script.sql" no banco de dados para criar o banco de dados<br>
Conecte-se ao banco de dados xyinc que foi criado pelo comando (\c xyinc;) e execute o script create-tables-script.sql para criar as tabelas necessárias<br><br>
Para criar um POI, é necessário fazer uma chamada PUT para a URL http://localhost:8080/xyIncLucas/rest/poi/create/{name}/{x}/{y}<br>
Para listar todos os POIs da base de dados, faça uma chamada GET para a URL http://localhost:8080/xyIncLucas/rest/poi<br>
Para listar todos os POIs que estão a uma distância d de um ponto x,y, faça uma chamada GET para a URL http://localhost:8080/xyIncLucas/rest/poi/nearest/{x}/{y}/{d}
