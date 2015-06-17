echo off

:echo ��ǰ�̷���%~d0
:echo ��ǰ�̷���·����%~dp0
:echo ��ǰ������ȫ·����%~f0
:echo ��ǰ�̷���·���Ķ��ļ�����ʽ��%~sdp0
:echo ��ǰCMDĬ��Ŀ¼��%cd%

F:

set WAR_NAME=cardexper-frontend-webapp.war

:����maven�����Ա�����
set MAVEN_OPTS=-Xms256m -Xmx512m -XX:ReservedCodeCacheSize=64m -XX:MaxPermSize=128m

:mvn_command
cd  %~dp0
echo ��ǰCMDĬ��Ŀ¼��%cd%

cd ../

:����Ϊ��ȡ�����������ǰCMDĬ��Ŀ¼������·����ֵ������Ŀ¼��
SET PROJECT_HOME=%cd%

echo ��ǰCMDĬ��Ŀ¼��%cd%

echo ========================================================
ECHO Project HOME:%PROJECT_HOME%
echo ========================================================

ECHO.
ECHO.
ECHO.
ECHO.
ECHO 1-����eclipse�����ļ�
ECHO.
ECHO 2-������������ֻ�����ϴ��޸Ĺ��Ĵ��룩
ECHO.
ECHO 3-ȫ��������ҵ����루����Ŀ������jar�����������������д���ʱʹ�ã�
ECHO.
ECHO 4-ȫ���������д��루����ҵ����룬���Դ��룬�ڰ�������������֤��Ŀ����ȷ�ԣ�
ECHO.
ECHO 5-ִ�����в���
ECHO.
ECHO 6-ִ������Ŀ����
ECHO.
ECHO 7-�������ļ������е�targetĿ¼��
ECHO.
ECHO 0-�˳��˵�

set /p isopt=��ѡ�����
if /i "%isopt%"=="1" goto mvn_eclipse
if /i "%isopt%"=="2" goto mvn_incremental_package
if /i "%isopt%"=="3" goto mvn_full_package
if /i "%isopt%"=="4" goto mvn_compile_all
if /i "%isopt%"=="5" goto mvn_test_all
if /i "%isopt%"=="6" goto mvn_test_project
if /i "%isopt%"=="7" goto mvn_clean_project
if /i "%isopt%"=="0" goto mvn_end

echo "��Чѡ���ѡ��(0-9)"
goto mvn_command

:mvn_eclipse
	ECHO.
	ECHO.
	ECHO.
	ECHO 1-��һ������eclipse�����ļ�
	ECHO.
	ECHO 2-��������eclipse�����ļ�
	ECHO.
	ECHO 0-����
	set /p eopt=��ѡ�����
	if /i "%eopt%"=="1" goto mvn_eclipse_first
	if /i "%eopt%"=="2" goto mvn_eclipse_again
	goto mvn_command

:mvn_eclipse_first
	cd %PROJECT_HOME%\
	echo ����eclipse�����ļ�
	start /HIGH mvn install eclipse:eclipse -Dmaven.test.skip -Pskip.attach.sources -Pskip.test.resources -Denforcer.skip -Denv=release
	goto mvn_command

:mvn_eclipse_again
	cd %PROJECT_HOME%\
	echo ����eclipse�����ļ�
	start /HIGH mvn eclipse:clean eclipse:eclipse
	goto mvn_command
	
:mvn_incremental_package
	cd %PROJECT_HOME%\
	echo ��ʼ������ʱ��: %time%

	start /HIGH  mvn clean package -Dmaven.test.skip=true -Pskip.attach.sources -Pskip.test.resources -Denforcer.skip 
	goto mvn_command
	
:mvn_full_package
	cd %PROJECT_HOME%\
	echo ��ʼ������ʱ��: %time%
	
	start /HIGH  mvn clean package -Dmaven.test.skip=true -Pmove.target -Pskip.attach.sources -Pskip.test.resources -Denforcer.skip 
	goto mvn_command

:mvn_compile_all
	cd %PROJECT_HOME%\
	start /HIGH mvn clean install -DskipTests=true -Pskip.attach.sources
	goto mvn_command

:mvn_test_all
	cd %PROJECT_HOME%\
	echo ��ʼִ�в���
	start /HIGH mvn clean test
	goto mvn_command
	
:mvn_test_project
	set /p subprj=��������Ŀ·����
	echo %PROJECT_HOME%\%subprj%
	cd %PROJECT_HOME%\%subprj%
	start /HIGH mvn test
	goto mvn_command

:mvn_clean_project
	cd %PROJECT_HOME%\
	echo ��ʼ������
	start /HIGH mvn clean 
	goto mvn_command
:mvn_end
cd %PROJECT_HOME%
