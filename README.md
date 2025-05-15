
# 💥 DosAttacker – Because Your Backend Deserves Some Cardio

## ⚠️ Disclaimer

> This is a **testing tool**. It's built to stress-test **your own infrastructure** or stuff you’ve got **explicit permission** to ~slap with requests~ load test.  
>  
> You *definitely* shouldn’t yeet it at production APIs you don’t own. We don't want to visit prison. Capisce? 😅

---

## 🚀 What Is This?

DosAttacker is a lightweight, multithreaded, no-nonsense HTTP spammer.  
Just a bunch of Java threads and a beefy `HttpClient` doing reps.

You give it a list of endpoints. It goes full gorilla mode.

💡 Built for fun. Used to find out which of your microservices cries first.

---

## 🧪 Real Talk

When you hit your own backend with this thing:

- 💥 Some services fold faster than a cheap lawn chair
- 🧯 Some load balancers beg for mercy
- 🐢 Some APIs hit 504 like it's their day job

🚑 **Confirmed fatalities** during testing:
- Laravel APIs on shared hosting – fell over instantly
- Spring Boot apps on cheap VPS – lights out in 3 seconds
- Gosu servers (GuildWire) – crashed so hard we got a 2003 RuneScape error page

Great for testing scalability, timeouts, and how your ops team reacts under pressure.

---

## 🔧 Setup?

Come on. It's Java.

- JDK 17+ and you're golden.
- `javac` it. `java` it. Ship it.

No special sauce required. No Spring Boot bloat. Just raw, juicy threads and fire.

---

## 🛠️ Project Structure

```text
DosStarter.java   -> Main launcher, spins up threads, prints stats
DosThread.java    -> One job: hit that endpoint until it dies or you do
DosStat.java      -> Just your local bean counter. Keeps score.
```

---

## ⚙️ Default Config

- Threads: `CPU cores x 4` (you can change that if you're brave)
- Timeout: 500ms
- User-Agent: `"Mozilla/5.0 TestBot"` – we’re classy like that
- Jitter: Adds random delay so we don’t look *too* much like a bot (even though we totally are)

---

## 📊 Output Example

```
--- Statistics ---
Total Requests:     13972
Failed Requests:    42
Traffic Sent (B):   314280
Traffic Recv (B):   1249891
Avg Time (ms):      187.33 ms
```

---

## 💡 Pro Tips

- Want to break things faster? Crank up the thread count.
- Want to be evil smart? Randomize headers or rotate params.
- Want to not get fired? Test on a local/staging env.

---

## 🧘 Philosophy

> "If it survives this, it'll survive production."  
> – Some overconfident dev, probably

---

## 📜 License

MIT. Do what you want, but **don’t be stupid**.
