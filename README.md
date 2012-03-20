JBing
=============

Este projeto é uma conjunto de funcionalidades para acessar a API do Bing, 
tanto para acesso do mecanismo de busca quanto para a ferramenta de tradução.

* Apenas o mecanismo de tradução está sendo apresentado aqui. Posteriormente, o método de busca será postado

Dependências
------------

Para execução destes códigos, deve-se utilizar uma biblioteca de manipulação 
de arquivos xml. Neste projeto foi aplicado a: [.JDom](http://www.jdom.org/)

Execução
--------

Para executar esta aplicação basta executar as seguintes linhas de comando:

* Compilar: `javac -cp REPOSITORIO_JDOM/jdom-1.1.2.jar TranslateTest.java`
* Executar: `java -cp REPOSITORIO_JDOM/jdom-1.1.2.jar TranslateTest`

Onde REPOSITORIO_JDOM representa o diretório onde está o arquivo jdom-1.1.2.jar. 
Pode-se utilizar outras versões do JDOM, entretanto, esse código não foi testado 
para versões diferentes.
