import 'package:flutter/material.dart';
import 'signup_page.dart';
import 'dashboard_page.dart';

class SignInPage extends StatefulWidget {
  const SignInPage({super.key});

  @override
  _SignInPageState createState() => _SignInPageState();
}

class _SignInPageState extends State<SignInPage> {
  double _bottomPosition = -500;
  bool _showOtpFields = false;
  bool _isOtpButtonVisible = true; // Track the visibility of the OTP button

  @override
  void initState() {
    super.initState();
    Future.delayed(const Duration(milliseconds: 10), () {
      setState(() {
        _bottomPosition = 0; // Move the white block to its final position
      });
    });
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      backgroundColor: const Color(0xFF3254ED),
      body: Stack(
        children: [
          Positioned(
            top: 50,
            left: MediaQuery.of(context).size.width * 0.25,
            child: Image.asset(
              'lib/images/signin_img.png',
              width: 200,
              height: 200,
            ),
          ),
          AnimatedPositioned(
            duration: const Duration(milliseconds: 500),
            bottom: _bottomPosition,
            left: 0,
            right: 0,
            child: Container(
              height: MediaQuery.of(context).size.height * 0.7,
              padding: const EdgeInsets.all(20),
              decoration: BoxDecoration(
                color: Colors.white,
                borderRadius: const BorderRadius.only(
                  topLeft: Radius.circular(40),
                  topRight: Radius.circular(40),
                ),
              ),
              child: Column(
                crossAxisAlignment: CrossAxisAlignment.center,
                children: [
                  const SizedBox(height: 40),
                  const Text(
                    'Sign in to continue',
                    style: TextStyle(
                      fontSize: 32,
                      fontWeight: FontWeight.bold,
                      color: Colors.black,
                    ),
                  ),
                  const SizedBox(height: 30),
                  TextField(
                    decoration: InputDecoration(
                      labelText: 'Mobile Number',
                      border: OutlineInputBorder(
                        borderSide: BorderSide(
                          color: const Color(0xFF3254ED),
                        ),
                      ),
                      contentPadding: const EdgeInsets.symmetric(
                          vertical: 12, horizontal: 16),
                    ),
                  ),
                  const SizedBox(height: 20),
                  // SEND OTP button visibility controlled by _isOtpButtonVisible
                  if (_isOtpButtonVisible)
                    ElevatedButton(
                      onPressed: () {
                        setState(() {
                          _showOtpFields = true;
                          _isOtpButtonVisible = false; // Hide the button when clicked
                        });
                      },
                      style: ElevatedButton.styleFrom(
                        padding: const EdgeInsets.symmetric(
                            vertical: 12, horizontal: 100),
                        backgroundColor: const Color(0xFF3254ED),
                        shape: RoundedRectangleBorder(
                          borderRadius: BorderRadius.circular(5),
                        ),
                      ),
                      child: const Text(
                        'SEND OTP',
                        style: TextStyle(fontSize: 18, color: Colors.white),
                      ),
                    ),
                  const SizedBox(height: 20),
                  if (_showOtpFields)
                    AnimatedOpacity(
                      opacity: _showOtpFields ? 1.0 : 0.0,
                      duration: const Duration(milliseconds: 500),
                      child: Column(
                        children: [
                          TextField(
                            obscureText: true,
                            decoration: InputDecoration(
                              labelText: 'OTP',
                              border: OutlineInputBorder(
                                borderSide: BorderSide(
                                  color: const Color(0xFF3254ED),
                                ),
                              ),
                              contentPadding: const EdgeInsets.symmetric(
                                  vertical: 12, horizontal: 16),
                            ),
                          ),
                          const SizedBox(height: 30),
                          ElevatedButton(
                            onPressed: () {
                              Navigator.push(
                                context,
                                MaterialPageRoute(builder: (context) => DashboardPage()),
                              );
                            },
                            style: ElevatedButton.styleFrom(
                              padding: const EdgeInsets.symmetric(
                                  vertical: 12, horizontal: 100),
                              backgroundColor: const Color(0xFF3254ED),
                              shape: RoundedRectangleBorder(
                                borderRadius: BorderRadius.circular(5),
                              ),
                            ),
                            child: const Text(
                              'SIGN IN',
                              style: TextStyle(fontSize: 18, color: Colors.white),
                            ),
                          ),
                        ],
                      ),
                    ),
                  const SizedBox(height: 20),
                  Row(
                    mainAxisAlignment: MainAxisAlignment.center,
                    children: [
                      const Text(
                        "Don't have an account? ",
                        style: TextStyle(
                          fontSize: 16,
                          color: Colors.black,
                        ),
                      ),
                      GestureDetector(
                        onTap: () {
                          Navigator.push(
                            context,
                            MaterialPageRoute(builder: (context) => const SignUpPage()),
                          );
                        },
                        child: const Text(
                          'Sign Up',
                          style: TextStyle(
                            fontSize: 16,
                            color: Color(0xFF3254ED),
                          ),
                        ),
                      ),
                    ],
                  ),
                  const SizedBox(height: 20),
                  const Divider(
                    color: Color(0xFFB0BEC5),
                    thickness: 1,
                    indent: 100,
                    endIndent: 100,
                  ),
                  const SizedBox(height: 20),
                  TextButton.icon(
                    onPressed: () {
                      // Handle Google Sign-In action
                    },
                    icon: Image.asset(
                      'lib/images/googleicon_img.png',
                      width: 24,
                      height: 24,
                    ),
                    label: const Text(
                      'Continue with Google',
                      style: TextStyle(
                        fontSize: 16,
                        color: Color(0xFF3254ED),
                      ),
                    ),
                    style: TextButton.styleFrom(
                      padding: const EdgeInsets.symmetric(vertical: 12, horizontal: 40),
                      backgroundColor: Colors.white,
                      shape: RoundedRectangleBorder(
                        borderRadius: BorderRadius.circular(5),
                        side: BorderSide(
                          color: const Color(0xFF3254ED),
                        ),
                      ),
                    ),
                  ),
                ],
              ),
            ),
          ),
        ],
      ),
    );
  }
}
