# O1-打包为jar

* 在pom中引入插件，插件位置可以自己新建一个spring项目看看
* version版本号与自己的spring版本一样

```
<build>
    <plugins>
        <plugin>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-maven-plugin</artifactId>
            <version>2.7.5</version>
        </plugin>
    </plugins>
</build>
```

* 然后取出jar包，并在旁边整一个bat文件

```
start http://localhost:8080/login.html
java -jar forge-0.0.1-SNAPSHOT.jar
pause
```