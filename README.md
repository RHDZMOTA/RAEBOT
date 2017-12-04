# OOP CHATBOT RAE
**Western Institute of Technology and Higher Education**


Twilio chatbot that serves as a dictionary for spanish language.

See API documentation: https://app.swaggerhub.com/apis/rhdzmota-apis/RAEBOT/1.0.0

## Requirements

1. Have [maven](https://maven.apache.org/download.cgi) installed.
2. JAVA 8 SDK.
3. { add content }

## Documentation

{ add content }

## Endpoints

### Word Definitions

Get requests to url-pattern **/define** with "word" parameter.

```bash
curl https://chatbot-rae.appspot.com/define?word=instancia
```
### Word Frequency

Get requests to url-pattern **/**

## Setup

Run a local simulation of the project by following these steps. 
  
1. Download this repository.
    * `git clone https://github.com/rhdzmota/OOP-CHATBOT-RAE`
2. Create an `application.conf` file.
    * `cp src/main/resources/application.conf.example src/main/resources/application.conf`
3. Run the following:
    * Local server: `mvn appengine:run`
    * You may need to: `gcloud auth login`

## Test

{ add content }
    
## Deployment
The following instructions illustrates the deployment procedure:

1. Set the project.
    * `gcloud config set project chatbot-rae`
2. From the base directory run:
    * `mvn appengine:deploy`
    
See https://chatbot-rae.appspot.com/
See https://raebot.herokuapp.com/
## Tech Stack

* [Maven](https://maven.apache.org)

## Authors

* **Rodrigo Hernández Mota** - _initial work and main developer_ - [rhdzmota](https://github.com/rhdzmota)
* **Luis Fernando**

## License

See **LICENSE.md** file.
