# Goal Map Drawing Application

This Java application is designed to read a goal map for a given candidate ID from a remote API and draw various shapes on a canvas. The shapes are represented by the `ToDraw` class, and the application supports creating and deleting different types of shapes such as "Cometh," "Polyanet," and "Soloon." The application employs multithreading to draw shapes in parallel, and it handles rate limiting from the API server by implementing a retry mechanism.

## Classes

### GoalMapReader
- `readGoalMap(String candidateId)`: Reads the goal map for the specified candidate ID from a remote API and returns a list of `ToDraw` objects representing the shapes to be drawn.

### ToDraw
- Represents a shape with properties `row`, `col`, and `shape`.

### AstralObjectHandler
- An abstract class providing a template for creating and deleting shapes.
- Implements a method for making HTTP requests with a retry mechanism.

### ComethHandler
- Extends `AstralObjectHandler` to handle "Cometh" shapes, including setting directions.

### PolyanetHandler
- Extends `AstralObjectHandler` to handle "Polyanet" shapes.

### SoloonHandler
- Extends `AstralObjectHandler` to handle "Soloon" shapes, including setting colors.

### DrawingManager
- Manages the drawing of shapes by creating appropriate handlers based on the shape type.
- Limits the number of threads to prevent API rate limiting.

### Solution
- The main class where the application is initiated.
- Reads the goal map, prints the shapes, and then draws them using the `DrawingManager`.

## Usage

1. Set the `apiUrl` variable in the `Solution` class to the base URL of the API.
2. Create an instance of `GoalMapReader`.
3. Obtain the list of shapes using `readGoalMap(candidateId)`.
4. Create an instance of `DrawingManager`.
5. Draw the shapes using `drawShapes(toDrawList, candidateId)`.

## Important Note

- The application is designed to handle rate limiting from the API server by implementing a retry mechanism with an increasing delay between retries.
- Ensure that the number of threads (`numThreads`) is set to an appropriate value to prevent 429 responses from the server.

Feel free to adjust the application based on your specific requirements and API server limitations.
