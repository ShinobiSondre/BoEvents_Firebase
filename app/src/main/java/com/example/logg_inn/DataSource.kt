package com.example.logg_inn

import com.example.logg_inn.models.DataModel

class DataSource {
    companion object {
        fun createDataset(): ArrayList<DataModel>{
            val list = ArrayList<DataModel>()
            list.add(
                DataModel(
                    "Søppel Plukking",
                    "Her ass was actually nerfed. It was bigger in her victory pose, but they reduced it.",
                    R.drawable.soppel,
                    "Fredrik rydder opp sitt eget søppel"
                )
            )
            list.add(
                DataModel(
                    "Party",
                    "And that is ok, dont's be a fucking incel biggot. It's the current year, get with the times",
                    R.drawable.party,
                    "Sondre"
                )
            )
            list.add(
                DataModel(
                    "Fjelltur",
                    "The popular shooting game Overwatch is getting a sequal LOL",
                    R.drawable.fjelltur,
                    "Magnus er på topp"
                )
            )
            list.add(
                DataModel(
                    "Grilling",
                    "I'm, trying to partner with Facebook Gaming, do you know what Facebook Gaming is? Anybody know what Facebook Gaming is? No, not Facebook, I think thats a social media website. I'm talkin' Facebook Gaming. Anyways, it's a two week old like, streaming platform that protects against unemployment and unwanted wage labor that may be looming in my future. That's my story, I emailed em a bunch of stuff. Sent 'em around the la inbox. Direct messages, stuff like that.",
                    R.drawable.grilling,
                    "DrDisrespect"
                )
            )
            list.add(
                DataModel(
                    "Dark Souls Speedrun",
                    "Vi skal arrangere en konkurranse hvor man skal spille gjennom dark souls 3 så fort som mulig.1000kr til den raskeste. Alle er velkommen til å delta. Runet må fullføres for at det skal godkjennes. Lykke til!",
                    R.drawable.speedrun,
                    "Pokimane"
                )
            )
            return list
        }
    }
}