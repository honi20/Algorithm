#include <iostream>
#include <string>
using namespace std;
int main() {
	char ch;
	string str;
	int result = 0;
	while (true) {
		result = 0;
		cin >> ch;
		if (ch == '#' ) break;
		getline(cin, str);
		for (int i = 0; i < str.size(); i++) {
			if (str.at(i) == ch || str.at(i) ==  ch + ('A' - 'a')) result++;
		}
		cout << ch<<" "<<result << "\n";
	}
}