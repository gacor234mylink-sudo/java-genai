package com.google.genai.examples;

import com.google.genai.Client;
import com.google.genai.types.GenerateContentConfig;
import com.google.genai.types.GenerateContentResponse;

/**
 * A simple random joke generator using the Google Gen AI SDK.
 * 
 * This example demonstrates how to use the Gemini API to generate random jokes
 * with different styles and formats. It showcases the flexibility of the SDK
 * for creative content generation.
 */
public class JokeGenerator {

  /**
   * Generates a random joke of a specified type.
   * 
   * @param jokeType The type of joke (e.g., "programming", "dad", "knock-knock", "science")
   * @return A joke as a string
   */
  private static String generateJoke(String jokeType) {
    Client client = new Client();
    
    String prompt = String.format(
        "Generate a single, original %s joke that is clean and funny. "+
        "Return only the joke without any additional commentary.",
        jokeType);
    
    GenerateContentResponse response =
        client.models.generateContent("gemini-2.5-flash", prompt, null);
    
    return response.text();
  }

  /**
   * Generates multiple random jokes in a batch.
   * 
   * @param count The number of jokes to generate
   * @param jokeType The type of joke
   * @return A formatted string containing multiple jokes
   */
  private static String generateMultipleJokes(int count, String jokeType) {
    Client client = new Client();
    
    String prompt = String.format(
        "Generate %d different %s jokes that are clean and funny. "+
        "Number each joke. Return only the jokes without additional commentary.",
        count, jokeType);
    
    GenerateContentResponse response =
        client.models.generateContent("gemini-2.5-flash", prompt, null);
    
    return response.text();
  }

  /**
   * Generates a joke and explains why it's funny.
   * 
   * @param jokeType The type of joke
   * @return A joke with an explanation
   */
  private static String generateJokeWithExplanation(String jokeType) {
    Client client = new Client();
    
    String prompt = String.format(
        "Generate a single, original %s joke. "+
        "Then briefly explain why this joke is funny in 1-2 sentences. "+
        "Format: Joke: [joke]. Why it's funny: [explanation].",
        jokeType);
    
    GenerateContentResponse response =
        client.models.generateContent("gemini-2.5-flash", prompt, null);
    
    return response.text();
  }

  /**
   * Demonstrates interactive joke generation with configuration.
   * This method shows how to use GenerateContentConfig for more control.
   * 
   * @return A randomly formatted joke
   */
  private static String generateCustomJoke() {
    Client client = new Client();
    
    // Configure the content generation
    GenerateContentConfig config =
        GenerateContentConfig.builder()
            .maxOutputTokens(150)
            .candidateCount(1)
            .build();
    
    String prompt = "Tell me a joke that involves wordplay or puns.";
    
    GenerateContentResponse response =
        client.models.generateContent("gemini-2.5-flash", prompt, config);
    
    return response.text();
  }

  public static void main(String[] args) {
    System.out.println("=== Random Joke Generator using Gemini API ===\n");
    
    try {
      // Example 1: Generate a single programming joke
      System.out.println("📝 Single Programming Joke:");
      System.out.println("-".repeat(50));
      String singleJoke = generateJoke("programming");
      System.out.println(singleJoke);
      System.out.println();
      
      // Example 2: Generate multiple dad jokes
      System.out.println("📝 Multiple Dad Jokes:");
      System.out.println("-".repeat(50));
      String multipleJokes = generateMultipleJokes(3, "dad");
      System.out.println(multipleJokes);
      System.out.println();
      
      // Example 3: Generate a joke with explanation
      System.out.println("📝 Science Joke with Explanation:");
      System.out.println("-".repeat(50));
      String jokeWithExplanation = generateJokeWithExplanation("science");
      System.out.println(jokeWithExplanation);
      System.out.println();
      
      // Example 4: Generate a custom joke with configuration
      System.out.println("📝 Custom Wordplay Joke:");
      System.out.println("-".repeat(50));
      String customJoke = generateCustomJoke();
      System.out.println(customJoke);
      System.out.println();
      
      System.out.println("✅ All jokes generated successfully!");
      
    } catch (Exception e) {
      System.err.println("❌ Error generating joke: " + e.getMessage());
      e.printStackTrace();
    }
  }
}