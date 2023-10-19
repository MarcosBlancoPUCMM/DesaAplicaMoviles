import 'package:curved_navigation_bar/curved_navigation_bar.dart';
import 'package:flutter/material.dart';
import 'package:tarea5/pages/page1.dart';
import 'package:tarea5/pages/page2.dart';

class NavigationBarController extends StatefulWidget {
  const NavigationBarController({super.key});

  @override
  State<NavigationBarController> createState() => _NavigationBarControllerState();
}

class _NavigationBarControllerState extends State<NavigationBarController> {
  int _selectedIndex = 0;

  void _navigateBottomBar(int index) {
    setState(() {
      _selectedIndex = index;
    });
  }

  final List<Widget> _pages = [
    const Page1(),
    const Page2(),
  ];

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      body: _pages[_selectedIndex],
      bottomNavigationBar: CurvedNavigationBar(
        backgroundColor: Colors.white,
        onTap: (index) {
          _navigateBottomBar(index);
        },
        color: Colors.lightBlueAccent.shade100,
        items: const [
          Icon(
              Icons.touch_app,
              color: Colors.white
          ),
          Icon(
              Icons.image,
              color: Colors.white
          ),
        ],
      ),
    );
  }
}
