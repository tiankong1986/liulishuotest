# liulishuotest


���������
��project��Ҫ��ʵ����һ�����û���ҽ�����ص�microservice�������ṩ�û���Ҳ�ѯ���û��������Լ��û�֮��Ľ��transfer�ȷ��� 

��Ҫ��ܣ�
1��ʹ��springMVC�ṩrestful webservice
2��ʹ��mybatis��ΪDAO��Ļ������
3��ʹ��C3PO���������ӳع������ݿ����ӵ��������
4������spring boot �ṩmicroservice���в��𻷾�
5������jvm�ṩ��HttpServer�����Դ��ģ����һ��С��httpserver��������java management extentions API��ϵõ���java���̵�jstack


��Ҫ�������ã�
jdk 1.6
mysql 5.6
maven 3.2.1
spring 4.0.X
spring boot 1.1.4release
������ο�pom.xml


�������ߣ�
eclipse

����ṹ��
1��coins-txn-conf��Ҫ�ṩһЩ������Ϣ������maven����Ŀ�����
2��coins-txn-mvn-import����import��Ŀ
3��coins-txn-core�ṩ����ҵ�����
4��coins-txn-api-ws�ṩrest����URL�Լ����𻷾�
5��db.sql��ص����ݿ�ű�

ע�����

�Ѵ��벿���Լ��Ļ����Ϻ󣬿�����Ҫ�޸��������ã�
1���޸�maven setting��coins-txn-conf�е�settings.coins.txn.ws.xml�����磬�Ѳֿ�ĳ��Լ��ĵ�
2���޸��Լ��Ĵ����װ�ű���coins-txn-conf�е�mvn build.bat��
3���޸��Լ������ݿ����ã�coins-txn-core�е�app.properties��
4��Ŀǰ�ṩ��api������8080�˿ڣ�jstack������8081�˿ڣ�������޸�api�˿ڿ�����coins-txn-api-ws�е�application.properties���ã����Ҫ�޸�jstack�˿ڣ���Ҫ�鿴���루����ʱ������أ�û�����������ļ����������������ƣ�
�д�������־�ȣ�
