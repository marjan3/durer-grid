# Dürer grid
> **Dürer grid** is a transparent grid that is placed between the object and the artist. Helps with training the eye to see proportions and angles.

![Image](header.png)

## Getting started 
To get a local copy up and running follow these simple example steps.

### Prerequisites
This section will describe requirements needed to satisfy the installation or running of the project.

 - Java 1.8
 - Maven
 
### Nice to have
 - Intellij

### Build
 - `git clone https://github.com/Tanevski3/durer-grid.git`
 - `cd ./durer-grid`
 - `mvn clean install`
 
### Run
 - Right click and run `MainEntry.java`
 
### Test
 - Run `mvn test`
 
### Deploy
 - `cd gui`
 - `mvn jfx:jar`
 - `mv gui/target/jfx/app/durer-grid-${version}-jfx.jar gui/target/jfx/app/durer-grid-${version}.jar`
 - `zip gui/target/jfx/app/. durer-grid-${version}`
 - `rm -rf dist/*`
 - `mv durer-grid-${version}.zip ../dist/`

## Usage guide
Use this space to show useful examples of how a project can be used. Additional screenshots, code examples and demos work well in this space. You may also link to more resources.  

## Download
 - The application is now available as a JAR file. Within the `dist/` directory of this repository the executable JAR file can be found.
 
## Contact

For contact, you can reach me at [marjantanevski@outlook.com](marjantanevski@outlook.com).

## License

MIT © [Marjan Tanevski](marjantanevski@outlook.com)
