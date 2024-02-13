

export type OAuthUser = {

  id: string;
  username: string;
  email: string;
  imageUrl: string;
  address: any;
  birthDate: string;
  phone: string;



}

export type SpringUser = {

  authorities: [];
  email: string;
  phone?: string; 
  username: string;
  dob: string | null;
  credentialsNonExpired: boolean;
  password: string;
  name: string;
  enabled: boolean;
  address: string;


}


