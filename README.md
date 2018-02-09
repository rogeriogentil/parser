# parser

A Java Tool to analyse a Web Server Access Log file.


## Construct

### Requirements to build

1. JDK 8
2. Apache Maven 3

### Building

Just execute:

    mvn clean install

## Running

Execute:

1. In the project's root directory:

    docker-compose up --build

2. In `target/` directory:

    java -cp "parser.jar" com.ef.Parser --help
    
Or, for instance:

    java -cp "parser.jar" com.ef.Parser --accesslog=/path/to/file --startDate=2017-01-01.00:00:00 --duration=daily --threshold=500
