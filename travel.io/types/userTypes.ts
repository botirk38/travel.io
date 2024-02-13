

export type OAuthUser = {

  id: string;
  name: string;
  email: string;
  imageUrl: string;
  address: any;
  birthDate: string;
  phoneNumber: string;



}

export type SpringUser = {

  authorities: [];
  email: string;
  phone: string | null;
  username: string;
  dob: string | null;
  credentialsNonExpired: boolean;
  password: string;
  name: string;
  enabled: boolean;
  address: string;


}


