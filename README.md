# java-ee-examination

**Designbeslut**

Jag har inte fokuserat jättemycket på design utan mest fokuserat på de många nya teknologierna som jag har använt i uppgiften. Min ambition har varit att hålla designen så simpel som möjligt. Jag har inte försökt komma på geniala lösningar till problem som kan lösas simpelt. För att förtydliga vad jag menar med design(jag tror att vi tänker lika) så föreslår jag att du jämför mitt arbete med Roberts/Johannes arbete. De har en väldigt smart design för deras sökfunktionalitet. Min är simpel och bygger på Spring Datas “Derived Query”-funktionalitet.

Jag tog lite inspiration från Robert/Johannes i ett fall och designade ett simpelt wrapper-objekt som har två fält, boolean hasError samt String message. Detta objekt returneras av UserService-klassens registerUser-metod. Controllern undersöker objektet för att se om det finns error, om så är fallet så mappas objektets meddelande till view-modellen för att presenteras för användaren. Detta kan kännas lite överflödigt i mitt fall då jag bara hanterar ett endaste error; att email redan är registrerad. I min användartabell krävs att email är unik.

Denna feature är en del av form-valideringen, det andra steget av valideringen kan man säga. Det första steget kontrollerar att input är giltig email.

**Arkitektur**

Jag valde att rendera GUI på server-sidan för att lära mig detta, det har varit roligt men samtidigt krångligt. Jag tycker att det är lättare att använda ramverk som Vue för att rendera i klienten. Då behöver man inte skicka information vidare till nästa rendering hela tiden, information om vilka sökpreferenser som var valda när användaren klickar på “sök” till exempel. Vi ska se vad du har att säga om hur jag har löst det problemet.

Å andra sidan tycker jag att det känns mycket bättre säkerhetsmässigt att rendera på server-sidan, det är lätt att reglera säkerheten och det känns stabilt. “Lätt” efter att jag med mycket om och men fått det att fungera över huvud taget.

Att ha varukorgen på servern var också smidigt, smidigare än att ha den på klientsidan. @SessionBased och ‘vips’ så slapp jag spara i diverse lokala cache-minnen som “LocalStorage”.

**Teknikval**

Spring, Thymeleaf, Spring Security, JSON Web Token, och Materialize CSS är de huvudsakliga teknikerna som jag har använt. Jag ville att hemsidan skulle se någorlunda snygg ut så jag använde CSS-biblioteket “Materialize”, då var det enkelt att få till ett helt acceptabelt GUI. JSON Web Token har jag använt för att säkra REST-lagret.

**Kodstruktur**

Min kod är strukturerad i paketen: controller, service, repository, model, security, resource, formdata, dto samt resource.

Resource innehåller REST-controllers.

Formdata innehåller “form backing bean” och dto innehåller “data transfer object”.

Formdata och dto är snarlika men har en skillnad, formdata innehåller endast de inbyggda typerna medan dto även får innehålla typer som jag har skapat. Jag valde att skilja på dem på detta viset då jag hade problem med den dynamiska dropdown-meny som admin använder för att välja kategori för ny produkt i formuläret för nya produkter. Jag tyckte att jag borde ha kunnat sätta de dynamiskt genererade option-elementens th:value-attribut till “nuvarande kategori i itereringen” men det ville sig inte, så jag angav istället kategori.id som th:value och lät handler-metoden slå upp kategorin på nytt. Det kändes dumt att slå upp kategorin på nytt efter att redan ha slagit upp en komplett lista.

Jag tror att resten säger sig självt gällandes paketen.

Av Rikard Virta