import 'package:flutter/material.dart';
import 'pages/intro_page.dart'; // Import the IntroPage

void main() {
  runApp(const MyApp());
}

class MyApp extends StatelessWidget {
  const MyApp({super.key});

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'Flutter Demo',
      theme: ThemeData(
        colorScheme: ColorScheme.fromSeed(seedColor: Colors.deepPurple),
        useMaterial3: true,
      ),
      // Set IntroPage as the initial screen
      home: IntroPage(),
    );
  }
}
