echo off

:echo 当前盘符：%~d0
:echo 当前盘符和路径：%~dp0
:echo 当前批处理全路径：%~f0
:echo 当前盘符和路径的短文件名格式：%~sdp0
:echo 当前CMD默认目录：%cd%

F:

set WAR_NAME=cardexper-frontend-webapp.war

:设置maven的属性变量；
set MAVEN_OPTS=-Xms256m -Xmx512m -XX:ReservedCodeCacheSize=64m -XX:MaxPermSize=128m

:mvn_command
cd  %~dp0
echo 当前CMD默认目录：%cd%

cd ../

:下面为获取该批处理命令当前CMD默认目录，并把路径赋值给工程目录；
SET PROJECT_HOME=%cd%

echo 当前CMD默认目录：%cd%

echo ========================================================
ECHO Project HOME:%PROJECT_HOME%
echo ========================================================

ECHO.
ECHO.
ECHO.
ECHO.
ECHO 1-生成eclipse工程文件
ECHO.
ECHO 2-增量编译打包（只编译上次修改过的代码）
ECHO.
ECHO 3-全量编译打包业务代码（在项目的依赖jar包升级或增量编译有错误时使用）
ECHO.
ECHO 4-全量编译所有代码（包括业务代码，测试代码，黑白名单，用于验证项目的正确性）
ECHO.
ECHO 5-执行所有测试
ECHO.
ECHO 6-执行子项目测试
ECHO.
ECHO 7-清理工程文件（所有的target目录）
ECHO.
ECHO 0-退出菜单

set /p isopt=【选择命令】
if /i "%isopt%"=="1" goto mvn_eclipse
if /i "%isopt%"=="2" goto mvn_incremental_package
if /i "%isopt%"=="3" goto mvn_full_package
if /i "%isopt%"=="4" goto mvn_compile_all
if /i "%isopt%"=="5" goto mvn_test_all
if /i "%isopt%"=="6" goto mvn_test_project
if /i "%isopt%"=="7" goto mvn_clean_project
if /i "%isopt%"=="0" goto mvn_end

echo "无效选项，请选择(0-9)"
goto mvn_command

:mvn_eclipse
	ECHO.
	ECHO.
	ECHO.
	ECHO 1-第一次生成eclipse工程文件
	ECHO.
	ECHO 2-重新生成eclipse工程文件
	ECHO.
	ECHO 0-返回
	set /p eopt=【选择命令】
	if /i "%eopt%"=="1" goto mvn_eclipse_first
	if /i "%eopt%"=="2" goto mvn_eclipse_again
	goto mvn_command

:mvn_eclipse_first
	cd %PROJECT_HOME%\
	echo 生成eclipse工程文件
	start /HIGH mvn install eclipse:eclipse -Dmaven.test.skip -Pskip.attach.sources -Pskip.test.resources -Denforcer.skip -Denv=release
	goto mvn_command

:mvn_eclipse_again
	cd %PROJECT_HOME%\
	echo 生成eclipse工程文件
	start /HIGH mvn eclipse:clean eclipse:eclipse
	goto mvn_command
	
:mvn_incremental_package
	cd %PROJECT_HOME%\
	echo 开始编译打包时间: %time%

	start /HIGH  mvn clean package -Dmaven.test.skip=true -Pskip.attach.sources -Pskip.test.resources -Denforcer.skip 
	goto mvn_command
	
:mvn_full_package
	cd %PROJECT_HOME%\
	echo 开始编译打包时间: %time%
	
	start /HIGH  mvn clean package -Dmaven.test.skip=true -Pmove.target -Pskip.attach.sources -Pskip.test.resources -Denforcer.skip 
	goto mvn_command

:mvn_compile_all
	cd %PROJECT_HOME%\
	start /HIGH mvn clean install -DskipTests=true -Pskip.attach.sources
	goto mvn_command

:mvn_test_all
	cd %PROJECT_HOME%\
	echo 开始执行测试
	start /HIGH mvn clean test
	goto mvn_command
	
:mvn_test_project
	set /p subprj=【输入项目路径】
	echo %PROJECT_HOME%\%subprj%
	cd %PROJECT_HOME%\%subprj%
	start /HIGH mvn test
	goto mvn_command

:mvn_clean_project
	cd %PROJECT_HOME%\
	echo 开始清理工作
	start /HIGH mvn clean 
	goto mvn_command
:mvn_end
cd %PROJECT_HOME%
