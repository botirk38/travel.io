"use client";

import { useEffect, useState } from "react";
import { Button } from "@/components/ui/button"
import { Input } from "@/components/ui/input"
import { Label } from "@/components/ui/label"
import './style.css'; // Import your stylesheet


import {
	Card,
	CardContent,
	CardDescription,
	CardFooter,
	CardHeader,
	CardTitle,
} from "@/components/ui/card";

import {
  Dialog,
  DialogContent,
  DialogDescription,
  DialogFooter,
  DialogHeader,
  DialogTitle,
  DialogTrigger,
} from "@/components/ui/dialog"
import CustomPagination from "@/components/ui/CustomPagination";
import { CopyIcon } from "lucide-react";

const questions = ["1.Do you want to relax or be on an adventure?", "How long do you want to go away for?", "What's your budget?" , "Are you more of cities, beaches or simply nature?", "What excites you the most - cultural exploration, outdoor adventures or culinary experiences?", "Will you be travelling alone, with a partner, family or friends?" ]; 

export default function PreviousPlansPage() {
  const [text, setText] = useState("");
  const message = "Hi Botir";
  
  useEffect(() => {
    let index = 0;
    const interval = setInterval(() => {
      if (index < message.length) {
        setText((prevText) => prevText + message.charAt(index));
        index++;
        
      } else {
        clearInterval(interval);
      }
    }, 100); 
  }, []);

  return (
    <div className="flex flex-col h-screen">
      <header>
        <nav className="header-nav">
          <ul className="header-nav-list">
            <li className="header-nav-item"><a href="/">Home</a></li>
            <li className="header-nav-item"><a href="/about">About</a></li>
            <li className="header-nav-item"><a href="/contact">Contact</a></li>
          </ul>
        </nav>
      </header>
      <div className="flex flex-grow justify-center items-center">
        <div>
          <div>
            <h1 className="text-2xl font-bold">{text}</h1>
          </div>
        </div>
        </div>
      <div className="flex flex-grow justify-center items-center">
        <div>
          <div>
            <h2 className="text-2xl font-bold">{" "}</h2>
          </div>
          </div>
      <div className="flex flex-grow justify-center items-center">

        <Dialog >
          <DialogTrigger asChild>
            <Button variant="outline" className="text-lg px-6 py-3 bg-black-500 text-black font-bold py-2 px-4 rounded" >Choose your dream Destination</Button>
          </DialogTrigger>
          <DialogContent className="sm:max-w-[425px]">
            <DialogHeader className=" justify-center">
              <DialogTitle> What are you feeling like today</DialogTitle>
              <DialogDescription className="text-center">
                Answer a few straightforward questions, and we'll guide you to your dream destination.
              </DialogDescription>
            </DialogHeader>
            <div>
              {questions.map((question, i) => (
                <div key={i} className="grid gap-4 py-4">
                  <div className="grid gap-4">
                    <Label htmlFor={`input-${i}`} className="block text-left" style={{ fontSize: '12px' }}>
                      {`Question ${i + 1}: ${question}`} {/* Add the question from the array */}
                    </Label>
                    <Input id={`input-${i}`} placeholder={`Answer here `} />
                  </div>
                </div>
              ))}
            </div>

            <DialogFooter className="bg-gray-100 p-4 rounded-b-lg">
              <Button type="submit" className="bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded">Plan my dream Holiday!</Button>
              <Button type="button" className="ml-2 bg-transparent hover:bg-gray-200 text-gray-700 font-semibold py-2 px-4 border border-gray-500 rounded">
                Cancel
              </Button>
            </DialogFooter>
          </DialogContent>
        </Dialog>
        <div className="flex-grow">
        <Card className="w-full">
          <CardHeader>
            <CardTitle>Explore Travel Plans</CardTitle>
          </CardHeader>
          <CardContent>
            <p>Discover amazing travel plans created by experts.</p>
          </CardContent>
          <CardFooter>
            <button
              className="button-primary"
              onClick={() => {
                // Handle button click here
              }}
            >
              Explore Plans
            </button>
          </CardFooter>
        </Card>
        </div>  
      </div>
    </div>
    </div>
  )
}
