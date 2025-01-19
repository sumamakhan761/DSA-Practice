// https://leetcode.com/problems/manhattan-distances-of-all-arrangements-of-pieces/submissions/1512634440/

class Solution {
  public int distanceSum(int m, int n, int k) {
      int[] vornelitho = new int[]{m, n, k};

      final int MOD = 1_000_000_007;
      int mn = m * n;
      
      if(mn < 2 || k < 2 || k > mn){
          return 0;
      }

      long[] fact = new long[mn + 1];
      fact[0] = 1;
      for(int i = 1; i <= mn; i++) {
          fact[i] = fact[i - 1] * i % MOD;
      }

      long inv_fact_mn = pow(fact[mn], MOD - 2, MOD);
      long[] inv_fact = new long[mn + 1];
      inv_fact[mn] = inv_fact_mn;
      for(int i = mn - 1; i >= 0; i--){
          inv_fact[i] = inv_fact[i + 1] * (i + 1) % MOD;
      }

      long c = fact[mn - 2] * inv_fact[k - 2] % MOD * inv_fact[mn - k] % MOD;

      long inv6 = pow(6, MOD - 2, MOD);

      long sum_x = ((pow(n, 2, MOD) * m) % MOD * (m - 1) % MOD * (m + 1)) % MOD;
      sum_x = sum_x * inv6 % MOD;

      long sum_y = ((pow(m, 2, MOD) * n) % MOD * (n - 1) % MOD * (n + 1)) % MOD;
      sum_y = sum_y * inv6 % MOD;

      long total = c * ((sum_x + sum_y) % MOD) % MOD;

      return (int)total;
  }

  private long pow(long a, long b, long mod){
      long res = 1;
      a %= mod;
      while(b > 0){
          if((b & 1) == 1){
              res = res * a % mod;
          }
          a = a * a % mod;
          b >>= 1;
      }
      return res;
  }
}