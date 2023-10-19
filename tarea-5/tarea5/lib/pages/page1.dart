import 'dart:ffi';
import 'dart:math';

import 'package:flutter/material.dart';
import 'package:google_fonts/google_fonts.dart';

class Page1 extends StatefulWidget {
  const Page1({super.key});

  @override
  State<Page1> createState() => _Page1State();
}

class _Page1State extends State<Page1> {
  int _current_index = 0;
  int _index = 0;

  final List<Color> _colors = [
    Colors.pinkAccent.shade100,
    Colors.redAccent.shade100,
    Colors.orangeAccent.shade100,
    Colors.yellowAccent.shade100,
    Colors.lightGreenAccent.shade100,
    Colors.lightBlueAccent.shade100,
    Colors.purpleAccent.shade100,
  ];

  final List<Color> _outlineColor = [
    Colors.pinkAccent,
    Colors.redAccent,
    Colors.orangeAccent,
    Colors.yellowAccent,
    Colors.lightGreenAccent,
    Colors.lightBlueAccent,
    Colors.purpleAccent,
  ];

  final List<TextStyle> _textStyle = [
    GoogleFonts.lobster(
      fontSize: 60,
      color: Colors.white,
    ),
    GoogleFonts.dancingScript(
      fontSize: 60,
      color: Colors.white,
    ),
    GoogleFonts.pacifico(
      fontSize: 60,
      color: Colors.white,
    ),
    GoogleFonts.alumniSansCollegiateOne(
      fontSize: 60,
      color: Colors.white,
    ),
    GoogleFonts.tiltNeon(
      fontSize: 60,
      color: Colors.white,
    ),
  ];

  Random random = Random();

  Color _selectedColor = Colors.lightBlueAccent.shade100;
  Color _selectedOutlineColor = Colors.lightBlueAccent;
  TextStyle _selectedTextStyle = GoogleFonts.lobster(
    fontSize: 60,
    color: Colors.white,
  );

  void _shuffleStyle() {
    setState(() {
      while (_current_index == _index) {
        _index = random.nextInt(_colors.length);
      }
      _current_index = _index;
      _selectedColor = _colors[_index];
      _selectedOutlineColor = _outlineColor[_index];
      _selectedTextStyle = _textStyle[random.nextInt(_textStyle.length)];
    });
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: const Text('On Pressed'),
        centerTitle: true,
        backgroundColor: Colors.lightBlueAccent.shade100,
      ),
      backgroundColor: Colors.white,
      body: GestureDetector(
        onTap: () {
          _shuffleStyle();
        },
        child: Container(
          margin: const EdgeInsets.all(20),
          alignment: Alignment.center,
          decoration: BoxDecoration(
              borderRadius: BorderRadius.circular(10),
              border: Border.all(
                color: _selectedOutlineColor,
                width: 2,
              ),
          ),
          child: Container(
            margin: const EdgeInsets.all(10),
            alignment: Alignment.center,
            decoration: BoxDecoration(
                borderRadius: BorderRadius.circular(10),
                boxShadow: [
                  BoxShadow(
                    color: _selectedColor,
                  ),
                ]
            ),
            child: Text('Tap me!',
              style: _selectedTextStyle,
            ),
          )
        ),
      ),
    );
  }
}
