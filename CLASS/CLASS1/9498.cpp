#include <iostream>
#include <vector>
#include <algorithm>
#include <map>
#include <queue>
#include <tuple>
#include <cstring>
#include <string>
#include <stack>
using namespace std;
typedef long long ll;

int main(){
    cin.tie(0); cout.tie(0);
    ios::sync_with_stdio(false);

    int n;
    cin>>n;
    if (n<=100 && n>=90)
        cout<<"A";
    else if (n<=89 && n>=80)
        cout<<"B";
    else if (n<=79 && n>=70)
        cout<<"C";
    else if (n<=69 && n>=60)
        cout<<"D";
    else
        cout<<"F";
}
