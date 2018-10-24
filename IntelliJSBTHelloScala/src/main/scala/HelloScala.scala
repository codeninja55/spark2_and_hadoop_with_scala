import org.apache.spark.{SparkConf, SparkContext}

object HelloScala {
    def main(args: Array[String]): Unit = {
        // create a SparkContext to initialise Spark

        val conf = new SparkConf()
        conf.setMaster("local")
        conf.setAppName("Word Count")
        val sc = new SparkContext(config = conf)

        // load the text into a Spark RDD, which is a distributed representation of each line of text
        val text_file = sc.textFile(path = "src/main/resources/shakespeare.txt")
        println(text_file)
        // count the words
        val counts = text_file.flatMap(line => line.split(" ")).map(word => (word, 1)).reduceByKey(_ + _)
        counts.foreach(println)
        System.out.println("Total words: " + counts.count())
        counts.saveAsTextFile("/tmp/shakespeare_word_count")
    }
}
