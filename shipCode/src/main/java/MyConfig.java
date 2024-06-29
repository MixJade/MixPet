interface MyConfig {
    /**
     * 需要生成的表名
     */
    String[] tableNames = new String[]{"Adopt"};

    /**
     * 数据库链接、账号、密码
     */
    String url = "jdbc:mysql://localhost:3306/pet_ship?useSSL=true",
            username = "root",
            password = "root";

    /**
     * 输出目录的路径，到项目名称为止
     */
    String outputDir = "/home/mixjade/mySoft/MixPet/shipPet";

    /**
     * 软件包、entity文件的包路径
     */
    String parentPack = "com.ship",
            entityPack = "model.entity",
            mapperPack = "mapper";

    /**
     * MapperXml文件的路径
     */
    String mapperXmlDir = "/src/main/resources/com/ship/mapper";

    /**
     * 文件作者
     */
    String author = "MixJade";
}
