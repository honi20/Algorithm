## CLASS 1
### 1008. A/B
- 소수점 출력
  ``` c++
  cout<<fixed;
  cout.precision(9);
  cout<<x;
  ```

### 1152. 단어의 개수
- 문자열 공백 포함 받기 : getline(cin,str);

### 1157. 단어 공부
- 아스키코드 : 'A'=65  , 'a'=97

--------
## CLASS 2
### 2609. 최대공약수와 최소공배수
- 최대공약수
  ``` c++
  ll gcd(ll x, ll y){         // 최대공약수
    if (!y)
        return x;
    return gcd(y, x % y);
  }
  
  ll gcd(int x, int y){
	  int temp = 0;
	  while (y){
		  temp = x%y;
		  x=y;
		  y=temp;
	  }
	  return x;
  }
  ```
- 최소공배수 = a * b / gcd(a,b)

### 1978. 소수 찾기
- 에라토스테네스의 체
``` c++
ll sieve[1005];
void init(){
    sieve[1]=1;
    for (ll i=2; i<1005; i++){
        if (sieve[i])       //0이 아니면 합성수임
            continue;
        for (ll j=i*i; j<1005; j+=i)    //시간 복잡도 보장
        //for (ll j=i+i; j<101010; j+=i)  //응용문제에서 주로 사용
            sieve[j]=1;
    }
}
```
