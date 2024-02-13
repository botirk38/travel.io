import { useEffect, useState } from 'react';
import { useRouter } from 'next/navigation';
import { getUserInfoClient } from '@/api/user/userInfoClient';

function useCheckUserInfo() {
  const [data, setData] = useState(null);
  const [isLoading, setIsLoading] = useState(true);
  const [error, setError] = useState(null);
  const router = useRouter();

  useEffect(() => {
    async function checkUserInfo() {
      try {
        setIsLoading(true);
        const response = await getUserInfoClient();
        if (!response) {
          router.push("/");
        } else {
          const jsonData = await response.json();
          console.log("data:", jsonData);
          setData(jsonData);
        }
      } catch (err : any) {
        console.log(err);
        setError(err);
      } finally {
        setIsLoading(false);
      }
    }

    checkUserInfo();
  }, [router]);

  return { data, isLoading, error };
}

export default useCheckUserInfo;

