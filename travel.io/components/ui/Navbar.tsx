
import Image from "next/image"
import Link from "next/link"
import { Button } from "./button"

const Navbar = () => {
  return (
    <><nav className="flexBetween max-container padding-container relative z-30 py-5">

    </nav><div className="lg:flexCenter hidden">
        <Button
          type="button"
          title="Login"
          icon="/user.svg"
      </div></>

    
  )
}

export default Navbar