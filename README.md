# Cloud Computing Project


Cloud Computing Project using Springboot and Angular with AWS Elastic Beanstalk

### Installation:
- Clone this project https://github.com/andt1606/news-api-aws.git (Backend)
- Open the project with IDE (Eclipse, IntelliJ,...)
- Open terminal in IDE:
  - **mvn clean install**
- Open folder contains this project
  - access **target** folder
  - **spring-boot-1.0.jar** this .jar file is used to deploy to AWS Elastic Beanstalk

### Hosting AWS

**Host Backend using Elastic Beanstalk**
- Access Amazone Web Service 
- Choose RDS 
  - Select database type **MySql**
  - Template choose **Free tier**
  - Fill in the information of the database (username, password)
  - Public access we select **Yes**
  - Select **Create Database**
- Select the database we just created
  - We have to remember **Endpoint & port** information to connect database
  - Setting Security Inbound Rules **TCP** **MYSQL/Aurora**
- Open the project with IDE (Eclipse, IntelliJ,...)
  - Edit **application.properties** in (src/resources/application.properties)
  - Using **Endpoint & port** above **Example**: spring.datasource.url = jdbc:mysql://**newspringboot.cefwl956lhot.ap-southeast-1.rds.amazonaws.com**:3306/newspringboot
- Back to Amazone Web Service choose **Elastic Beanstalk**
  - Fill Application name
  - In Platform we choose **Java**, **Corretto 8 running on 64bit Amazon Linux 2**
  - Select **Upload your code**
  - Choose file **spring-boot-1.0.jar** we have built in the **Installation**
  - Select **Create Environment**
  - After created you will have a link same same like this Eg: Springbootnews-env.eba-sexadeey.ap-southeast-1.elasticbeanstalk.com
  - Access **Environment Proteties**
    - Add PORT
    - Value 8081
    - Apply
- Back to Amazone Web Service choose **IAM**
  - Access **Roles** select **aws-elasticbeanstalk-ec2-role**
  - Find **RDSFullAccess**, **S3FullAccess** choose **Attach Policy**

**Host Frontend using Elastic Beanstalk**
- Access this github https://github.com/superprokid/ADNews and follow the tutorial to Host Frontend

### API
- http://springbootnews-env.eba-sexadeey.ap-southeast-1.elasticbeanstalk.com/
- Request:
  - /news : get all articles
  - /new/id  : get one article with id 
  - /categories : get all category
  - /category/id :  get one category with id 

### TEST
- Open your Browser
- Navigate to http://springbootnews-env.eba-sexadeey.ap-southeast-1.elasticbeanstalk.com/
- Request http://springbootnews-env.eba-sexadeey.ap-southeast-1.elasticbeanstalk.com/news to get all articles
- Request http://springbootnews-env.eba-sexadeey.ap-southeast-1.elasticbeanstalk.com/categories to get all category
- Wait for Result
