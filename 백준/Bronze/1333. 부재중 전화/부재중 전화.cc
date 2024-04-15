#include <iostream>
using namespace std;
int main() {
	int L, M, N;
	cin >> L >> M >> N;
	int endMusic = (L*(M + 5));
	int result = 0;
	while (true) {
		if (result % N == 0 && (result >= endMusic || 5 >= (M + 5) - result % (M + 5))) {
			cout << result;
			break;
			return 0;
		}
		result++;
	}
}