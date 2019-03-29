# job market

A course project built on the requirement from lecturer. Only for learning purpose.

> requirements document are located in ./doc

## roles

- freelancer
- job provider
- administrator

## techniques

EJB / JPA / MDB / JSF     

based on JDK8
(*for Glassfish5 container*)

## running instruction

- run glassfish server and mysql database
- import `./setup/glassfish-resources.xml` to setup glassfish.
  - command: `asadmin add-resources ./setup/glassfish-resources.xml`
- in case using IDEA as IDE, after importing maven project you can add running configuration now
  - Add `Glassfish Server/local`
  - in `deployment` tab, add artifact `war expoloded`/`jar expoloded`

> tip: you may need to add glassfish into `File | Settings | Build, Execution, Deployment | Application Servers` firstly.

## some pitfalls met are recorded on

> Chinese only

<https://orangejuice.cc/code/ejb-project-establishing/>

## Demo

Guest

![demo](img/Annotation%202019-03-29%20001652.jpg)

![demo](img/Annotation%202019-03-29%20001756.jpg)

### freelancer

![demo](img/Annotation%202019-03-28%20224238.jpg)

![demo](img/Annotation%202019-03-28%20224332.jpg)

![demo](img/Annotation%202019-03-28%20224356.jpg)

![demo](img/Annotation%202019-03-29%20001532.jpg)

### provider

![demo](img/Annotation%202019-03-29%20001726.jpg)

### administrator

![demo](img/Annotation%202019-03-29%20001919.jpg)

![demo](img/Annotation%202019-03-29%20001942.jpg)
