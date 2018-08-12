close all;

load('output.csv');

simulation_size = size(output);
numberOfParticles = simulation_size(2);

%% Analyze simulation

time = output(:,1);

maxx = zeros(simulation_size(1),1);
maxy = zeros(simulation_size(1),1);
minx = zeros(simulation_size(1),1);
miny = zeros(simulation_size(1),1);

for row=1:simulation_size(1)
    particles = output(row, 2:2:end)';
    maxx(row) = max(particles(2:2:end-1));
    maxy(row) = max(particles(3:2:end));
    minx(row) = min(particles(2:2:end-1));
    miny(row) = min(particles(3:2:end));
end

figure;
subplot(2,2,1);
plot(time, maxx, '.');
title('Max x');
xlabel('t');

subplot(2,2,2);
plot(time, maxy, '.');
title('Max y');
xlabel('t');

subplot(2,2,3);
plot(time, minx, '.');
title('Min x');
xlabel('t');

subplot(2,2,4);
plot(time, miny, '.');
title('Min x');
xlabel('t');

meanx = zeros(simulation_size(1),1);
meany = zeros(simulation_size(1),1);
stdx = zeros(simulation_size(1),1);
stdy = zeros(simulation_size(1),1);

for row=1:simulation_size(1)
    particles = output(row, 2:2:end)';
    meanx(row) = mean(particles(2:2:end-1));
    meany(row) = mean(particles(3:2:end));
    stdx(row) = std(particles(2:2:end-1));
    stdy(row) = std(particles(3:2:end));
end

figure;
subplot(2,2,1);
plot(time, meanx, '.');
title('Mean x');
xlabel('t');

subplot(2,2,2);
plot(time, meany, '.');
title('Mean y');
xlabel('t');

subplot(2,2,3);
plot(time, stdx, '.');
title('\sigma_x');
xlabel('t');

subplot(2,2,4);
plot(time, stdy, '.');
title('\sigma_y');
xlabel('t');

%% Run simulation

time = output(:,1);
time(3:end) = time(3:end) - time(2:end - 1);
time = time ./ 1000;

figure;
xlim([-250 250]);
ylim([-250 250]);

for row=1:simulation_size(1)
    particles = output(row, 2:2:end)';
    plot(particles(2:2:end-1), particles(3:2:end), 'r.', 'MarkerSize', 5)
    maxx(row) = max(particles(2:2:end-1));
    maxy(row) = max(particles(3:2:end));
    minx(row) = min(particles(2:2:end-1));
    miny(row) = min(particles(3:2:end));
    drawnow();
    pause(time(row));
end